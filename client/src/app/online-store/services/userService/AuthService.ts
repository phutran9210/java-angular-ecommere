import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {apiUrl} from '../../../shared/http/apiHttp'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient,) {
  }

  login(credentials: { email: string; password: string }) {
    return this.http.post(`${apiUrl}/api/auth/login`, credentials);
  }
}
