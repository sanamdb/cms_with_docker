import { AnswerSheet } from "./AnswerSheet";

export interface ExamAnswerSheet {
    id: string;
	username: string;
	examId: string;
	submissionDateTime: Date;
	answerSheet: AnswerSheet[];
}