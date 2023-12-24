import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Router} from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private router: Router) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const accessToken = sessionStorage.getItem('ACCESS_TOKEN');
    let authReq = request;
    // Thêm token vào header nếu có
    if (accessToken) {
      authReq = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${accessToken}`)
      });
    }

    return next.handle(authReq).pipe(
      catchError((error: HttpErrorResponse) => {
        // Xử lý khi token hết hạn
        if (error.status === 40) {
          this.handleTokenExpiration();
        }
        return throwError(() => error);
      })
    );
  }

  private handleTokenExpiration() {
    // Cài đặt logic để xử lý token hết hạn, ví dụ:
    this.router.navigate(['/login']);
  }
}
