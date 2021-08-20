import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Question } from '../classes/question';

@Injectable({
  providedIn: 'root'
})
export class QuestionsService {

  private baseUrl = "http://localhost:9092";

  constructor(private httpClient: HttpClient) { }

  temp =  JSON.stringify(localStorage.getItem('token'));

  headers_object = new HttpHeaders().set("Authorization", this.temp);

  submitResponse(questionsList: Question[]){
  
    let questions ={ questionsEntity: questionsList};
    return this.httpClient.post(`${this.baseUrl}/questions`, questions, {headers: this.headers_object });   
 
  }
  
  }


  



