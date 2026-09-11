import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { interval, Subscription } from 'rxjs';
import { ExaminationDetail, AnswerItem, ExamSubmissionPayload, Question } from '../../interfaces/ExaminationDetail';
import { ExamService } from '../../services/exam.service';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.scss']
})
export class ExamComponent implements OnInit, OnDestroy {
  examId!: string;
  examData!: ExaminationDetail;
  subjects: string[] = [];
  currentSubject: string = '';
  currentIndex: number = 0;
  isSubmitting: boolean = false;
  isSubmitModalOpen: boolean = false;
  
  // Added alert modal properties
  submissionError: string = '';
  submissionSuccess: boolean = false;

  selectedAnswers: { [questionId: string]: string } = {};
  timeLeftSeconds: number = 0;
  timerFormatted: string = '00:00:00';
  private timerSub?: Subscription;
  isLoading: boolean = true;

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private examService: ExamService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(param => {
      this.examId = param['id'];
      if (this.examId) {
        this.examService.getExamPaper(this.examId).subscribe({
          next: (exam) => {
            this.examData = exam;
            this.subjects = Object.keys(exam.questions || {});
            if (this.subjects.length > 0) {
              this.currentSubject = this.subjects[0];
              this.currentIndex = 0;
            }
            this.calculateInitialTimer();
            this.isLoading = false;
          },
          error: (err) => { console.error(err); this.isLoading = false; }
        });
      }
    });
  }

  // --- Logic Helpers ---
  selectSubject(s: string) { this.currentSubject = s; this.currentIndex = 0; }
  getCurrentQuestions(): Question[] { return this.examData.questions[this.currentSubject] || []; }
  getCurrentQuestion(): Question | null { const q = this.getCurrentQuestions(); return q.length > 0 ? q[this.currentIndex] : null; }
  
  nextQuestion() {
    const q = this.getCurrentQuestions();
    if (this.currentIndex < q.length - 1) this.currentIndex++;
    else {
      const idx = this.subjects.indexOf(this.currentSubject);
      if (idx < this.subjects.length - 1) { this.currentSubject = this.subjects[idx + 1]; this.currentIndex = 0; }
    }
  }

  prevQuestion() {
    if (this.currentIndex > 0) this.currentIndex--;
    else {
      const idx = this.subjects.indexOf(this.currentSubject);
      if (idx > 0) { this.currentSubject = this.subjects[idx - 1]; this.currentIndex = this.getCurrentQuestions().length - 1; }
    }
  }

  goToQuestion(i: number) { this.currentIndex = i; }
  trackAnswer(id: string, val: string) { this.selectedAnswers[id] = val; }
  isAnswerSelected(id: string, val: string) { return this.selectedAnswers[id] === val; }
  isQuestionAnswered(id: string) { return !!this.selectedAnswers[id]; }
  getAnsweredCount() { return Object.keys(this.selectedAnswers).length; }
  getTotalQuestionsCount() { return this.examData ? Object.values(this.examData.questions).flat().length : 0; }

  // --- Timer & Submit ---
  calculateInitialTimer() {
    const end = new Date(this.examData.endDateTime).getTime();
    const now = new Date().getTime();
    this.timeLeftSeconds = Math.max(0, Math.floor((end - now) / 1000));
    this.startTimer();
  }

  startTimer() {
    this.timerSub = interval(1000).subscribe(() => {
      if (this.timeLeftSeconds > 0) { this.timeLeftSeconds--; this.updateTime(); }
      else { this.stopTimer(); this.submitExam(true); }
    });
  }

  updateTime() {
    const h = Math.floor(this.timeLeftSeconds / 3600);
    const m = Math.floor((this.timeLeftSeconds % 3600) / 60);
    const s = this.timeLeftSeconds % 60;
    this.timerFormatted = `${this.pad(h)}:${this.pad(m)}:${this.pad(s)}`;
  }

  pad(n: number) { return n < 10 ? '0' + n : n; }
  stopTimer() { if (this.timerSub) this.timerSub.unsubscribe(); }

  openSubmitConfirmation() { 
    this.isSubmitModalOpen = true; 
    this.submissionError = '';
    this.submissionSuccess = false;
  }

  closeSubmitConfirmation() { 
    if (!this.isSubmitting) {
      this.isSubmitModalOpen = false; 
    }
  }

  confirmAndSubmit() { 
    this.submitExam(true); 
  }

  submitExam(isAuto: boolean = false) {
    this.isSubmitting = true;
    this.submissionError = '';
    this.stopTimer();
    
    const payload: ExamSubmissionPayload = {
      examId: this.examId,
      answerSheet: Object.keys(this.selectedAnswers).map(id => ({ questionId: id, selectedAnswer: this.selectedAnswers[id] }))
    };

    this.examService.submitExam(payload).subscribe({
      next: () => { 
        this.isSubmitting = false; 
        this.submissionSuccess = true; // Triggers clean success view in modal
        setTimeout(() => {
          this.isSubmitModalOpen = false;
          this.router.navigate(['portal', 'student']); 
        }, 2000);
      },
      error: (e) => { 
        console.error(e); 
        this.isSubmitting = false; 
        this.submissionError = 'Error submitting exam. Please check your connection and try again.'; 
      }
    });
  }

  ngOnDestroy() { this.stopTimer(); }
}