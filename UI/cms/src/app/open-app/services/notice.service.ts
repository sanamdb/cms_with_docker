import { Injectable } from '@angular/core';
import { Notice } from '../interfaces/Notice';

@Injectable({
  providedIn: 'root'
})
export class NoticeService {

  notice: Notice[] = []
  constructor() { }

  getNotices(): Notice[] {
    this.notice = [

      {
        id: "1",
        date: new Date('07-08-2026'),
        title: 'Semester Exam Schedule Announced for All Undergraduate and Postgraduate Programs',
        description: 'Final examinations will commence from next month. Check your portal.',
        fullMessage: `Final examinations for the upcoming semester will officially commence from next month across all undergraduate and postgraduate departments. All students are required to verify their examination hall tickets, seating arrangements, and detailed subject-wise timetables directly through the student portal. 

                      Please ensure all pending library books, laboratory dues, and tuition fee payments are cleared before your respective hall ticket download link unlocks. Students failing to clear their dues will not be permitted to enter the examination halls under any circumstances.

                      Important Guidelines for Students:
                      1. Entry to the examination hall will begin 30 minutes prior to the scheduled start time. No candidate will be allowed entry 15 minutes after the exam commences.
                      2. Carrying a valid College Identity Card along with the printed Hall Ticket is strictly mandatory for all sessions.
                      3. Programmable calculators, smartwatches, mobile phones, and any form of unauthorized electronic devices or written material inside the examination hall are strictly prohibited and will invite immediate disciplinary action under the university malpractice guidelines.
                      4. If there are any clashes in your subject dates or discrepancies in your personal details printed on the hall ticket, report them to the examination cell or your respective department coordinator immediately before the end of this week.

                      We wish all students the very best of luck with your preparations. Make sure to get adequate rest and review the official syllabus guidelines provided by your faculty members.`,
                              author: 'Dr. R. Sharma (Controller of Examinations)'
      },
      {
        id: "2",
        date: new Date('07-05-2026'),
        title: 'Annual Technical Fest "Innovate 2026" Registration and Project Submission Guidelines',
        description: 'Registrations are now open for coding, hackathons, and hardware exhibits.',
        fullMessage: 'The annual techno-cultural symposium "Innovate 2026" is back! We invite enthusiastic developers, designers, and engineers to register for the 24-hour hackathon, robotics challenge, and paper presentation tracks. Winning teams stand a chance to win cash prizes and direct internship interviews with premier tech partners. Visit the fest desk in the main atrium for registration details.',
        author: 'Prof. Ananya Desai (Faculty Coordinator)'
      },
      {
        id: "3",
        date: new Date('06-09-2026'),
        title: 'Library Extended Working Hours During Examination Preparation Week',
        description: 'Central library will remain open 24/7 starting next Monday.',
        fullMessage: 'To support students with their final exam preparation, the central library management has announced extended operational hours. Starting next Monday, the reading halls, reference sections, and digital resource labs will remain open 24 hours a day, 7 days a week. Valid college ID cards are mandatory for late-night entry and exit tracking.',
        author: 'Chief Librarian'
      },
      {
        id: "4",
        date: new Date('05-03-2026'),
        title: 'Mandatory Campus Wi-Fi Network Upgrade and Security Certificate Renewal',
        description: 'Campus internet services will experience downtime this Saturday.',
        fullMessage: 'Please be advised that the IT Infrastructure Department will be performing a comprehensive network infrastructure upgrade and security certificate renewal this coming Saturday between 1:00 AM and 6:00 AM. During this maintenance window, campus Wi-Fi, hostel internet access, and the student portal services will be completely offline. We regret any inconvenience caused.',
        author: 'IT Infrastructure Team'
      },
      {
        id: "5",
        date: new Date('04-28-2026'),
        title: 'Placement Cell Notice: Pre-Placement Talk and Resume Drop Guidelines for Tier-1 Companies',
        description: 'Final year students must update their resumes on the placement portal.',
        fullMessage: 'The Training and Placement Cell is hosting mandatory pre-placement talks for upcoming recruitment cycles involving core software and financial enterprises. All eligible final-year students must update their latest academic transcripts, project portfolios, and resumes on the placement portal before the strict cut-off deadline this Friday evening.',
        author: 'Placement Office'
      }
    ];
    return this.notice;
  }
}
