import { Injectable } from '@angular/core';
import { FAQ } from '../interfaces/FAQ';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FaqService {

  faq: FAQ[] = [];
  
    constructor(
      private http: HttpClient
    ) { }
  
    allFaq(): Observable<FAQ[]> {
      return this.http.get<FAQ[]>('/college/api/v1/faq'); 
    }

    createFaq(faq: FAQ): Observable<FAQ> {
      return this.http.post<FAQ>('/college/api/v1/faq', faq);
    }

}
