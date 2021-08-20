import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StatusService {

  constructor(private httpClient: HttpClient) { }
  
  private baseUrl = "http://localhost:9092";

  temp =  JSON.stringify(localStorage.getItem('token'));

  headers_object = new HttpHeaders().set("Authorization", this.temp);


  getStatus(){
     return this.httpClient.get(`${this.baseUrl}/status`, {headers: this.headers_object});
  }
}
