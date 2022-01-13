import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';

const AUTH_API = 'http://localhost:8080/api/public/';

/*
Creator: PhuocPD
 */
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  httpOptions: any;
  isLoggedIn: boolean;

  constructor(private httpClient: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    };
  }

  login(obj): Observable<any> {
    return this.httpClient.post<any>(AUTH_API + 'login', {
      username: obj.username,
      password: obj.password
    }, this.httpOptions);
  }

  getRegister(code: string): Observable<any> {
    return this.httpClient.get<any>(AUTH_API + 'register/' + code);
  }

  register(obj: any, code: string): Observable<any> {
    return this.httpClient.post<any>(AUTH_API + 'register/' + code, {
      username: obj.username,
      password: obj.password,
      role: obj.role
    });
  }
  editRegister(obj: any, code: string): Observable<any> {
    return this.httpClient.patch<any>(AUTH_API + 'register-edit-password/' + code, {
      username: obj.username,
      password: obj.password,
      role: obj.role
    });
  }
}
