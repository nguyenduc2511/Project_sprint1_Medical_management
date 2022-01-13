import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Producer} from '../model/producer';

@Injectable({
  providedIn: 'root'
})
export class ProducerService {
  private API_URL = 'http://localhost:8080';
  constructor(private http: HttpClient) { }
  getListProducer(): Observable<Producer[]> {
    return this.http.get<Producer[]>(this.API_URL + '/api/admin/supplies/producer');
  }
}
