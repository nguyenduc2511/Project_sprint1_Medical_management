import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Role} from '../model/role';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private url = 'http://localhost:8080/api/public/role';

  constructor(private httpClient: HttpClient) {
  }

  findAll(): Observable<Role[]> {
    return this.httpClient.get<Role[]>(this.url);
  }
}
