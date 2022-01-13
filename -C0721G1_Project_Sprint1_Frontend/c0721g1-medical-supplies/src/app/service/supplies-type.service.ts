import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SuppliesType} from '../model/supplies-type';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SuppliesTypeService {
  private API_URL = 'http://localhost:8080';
  constructor(private http: HttpClient) { }
  getListSuppliesType(): Observable<SuppliesType[]> {
    return this.http.get<SuppliesType[]>(this.API_URL + '/api/admin/supplies/suppliestype');
  }
}
