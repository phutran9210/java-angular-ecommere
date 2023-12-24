import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {apiUrl} from '../../../shared/http/apiHttp'
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,) {
  }

  login(credentials: { email: string; password: string }) {
    return this.http.post(`${apiUrl}/api/auth/login`, credentials, {observe: "response", withCredentials: true});
  }

  register(credentials: { email: string; password: string }) {
    return this.http.post(`${apiUrl}/api/auth/register`, credentials, {observe: "response"});
  }

  checkLoginStatus(): Observable<any> {
    return this.http.get(`${apiUrl}/api/auth/is-login`, {observe: "response", withCredentials: true});
  }

  saveAccessToken(accessToken: string): void {
    sessionStorage.setItem('ACCESS_TOKEN', accessToken);
  }
}
