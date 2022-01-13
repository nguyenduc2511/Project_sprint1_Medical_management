import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TrendingSupplies} from '../model/TrendingSupplies';

@Injectable({
  providedIn: 'root'
})
export class TrendingSuppliesService {
  private API = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getAll(): Observable<TrendingSupplies | any> {
    return this.http.get<TrendingSupplies | any>(this.API + '/user/stats/supplies-stats/trending-supplies');
  }
}
