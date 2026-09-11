export interface Question {
  id: string;
  departmentId: string;
  batch: string;
  question: string;
  subject: string;
  option1: string;
  option2: string;
  option3: string;
  option4: string;
}

export interface QuestionMap {
  [subject: string]: Question[];
}

export interface ExaminationDetail {
  id: string;
  examName: string;
  departmentId: string;
  batch: string;
  startDateTime: string;
  endDateTime: string;
  description: string;
  questions: QuestionMap;
}

export interface AnswerItem {
  questionId: string;
  selectedAnswer: string;
}

export interface ExamSubmissionPayload {
  examId: string;
  answerSheet: AnswerItem[];
}