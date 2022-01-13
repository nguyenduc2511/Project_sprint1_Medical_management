import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PotentialCustomer} from '../model/PotentialCustomer';

@Injectable({
  providedIn: 'root'
})
export class PotentialCustomerService {

  private API = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<PotentialCustomer[] | any> {
    return this.http.get<PotentialCustomer[] | any>(this.API + '/user/stats/potential-customer');
  }

  searchCustomerStats(startDate: string, endDate: string): Observable<PotentialCustomer[] | any> {
    // tslint:disable-next-line:max-line-length
    return this.http.get<PotentialCustomer[] | any>(this.API + '/user/stats/potential-customer/fetch/' + startDate + '/' + endDate);
  }

}
