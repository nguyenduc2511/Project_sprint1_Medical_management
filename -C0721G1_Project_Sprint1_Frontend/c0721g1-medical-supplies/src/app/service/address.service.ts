// @ts-ignore
import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Address} from '../model/address';

const API = 'http://localhost:8080/api/public/address/';

// @ts-ignore
@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(
    private http: HttpClient
  ) {}
  // @ts-ignore
  getList(): Observable<Address[]> {

    return this.http.get<Address[]>(API);

  }
}
