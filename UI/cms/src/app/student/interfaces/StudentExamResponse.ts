import { ExamAnswerSheet } from "./ExamAnswerSheet";
import { Examination } from "./Examination";
import { ExamResult } from "./ExamResult";

export interface StudentExamResponse {
    exam: Examination;
	submission: ExamAnswerSheet;
	result: ExamResult;
}