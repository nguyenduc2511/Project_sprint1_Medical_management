import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../model/user';
import {Observable} from 'rxjs';
import {PasswordChange} from '../model/PasswordChange';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private API_URL = 'http://localhost:8080/api/user/';

  constructor(private http: HttpClient) {
  }

  changePass(user: User, oldPass: string): Observable<void> {
    return this.http.patch<void>(this.API_URL + 'editPass/' + oldPass, user);
  }

  find(id: number): Observable<User> {
    return this.http.get<User>(this.API_URL + 'find/' + id);
  }

  changePassword(id: number, oldPassword: string, newPassword: string): Observable<any> {
    // @ts-ignore
    return this.http.patch<any>(this.API_URL + 'change-password/' + id + '/' + oldPassword + '/' + newPassword);
  }

  getAllUser(): Observable<any> {
    return this.http.get<any>(this.API_URL + 'all-user');
  }
}
