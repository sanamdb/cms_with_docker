import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ExaminationDetail, ExamSubmissionPayload } from '../interfaces/ExaminationDetail';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ExamService {

  constructor(private http: HttpClient) { }

  getExamPaper(id: string): Observable<ExaminationDetail> {
    return this.http.get<ExaminationDetail>(`/exam/api/v1/examination/${id}`);
  }

  submitExam(payload: ExamSubmissionPayload): Observable<any> {
    return this.http.post<any>(`/exam/api/v1/submit`, payload);
  }
}