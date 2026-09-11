import { Injectable } from '@angular/core';
import { FAQ } from '../interfaces/FAQ';
import { HttpClient, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { get } from 'http';

@Injectable({
  providedIn: null
})
export class AboutUsService {

  faq: FAQ[] = [];

  constructor(
    private http: HttpClient
  ) { }

  allFaq(): Observable<FAQ[]> {
    return this.http.get<FAQ[]>('/college/api/v1/faq'); 
  }

  getAllPhotos() {
    let request= new HttpRequest('GET', 'https://jsonplaceholder.typicode.com/photos', {reportProgress: true})
    this.http.request(request).subscribe(data => {
      console.log(data);
      console.log(data.type);
    })
  }

  getCacheFaq() {
    return this.http.get<FAQ[]>('/college/api/v1/faq');
  }

}
