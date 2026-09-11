export interface Examination {
    id: string;
	examName: string;
	departmentId: string;
	batch: string;
	startDateTime: Date;
	endDateTime: Date;
	description: string;
	questionId: string[];
}