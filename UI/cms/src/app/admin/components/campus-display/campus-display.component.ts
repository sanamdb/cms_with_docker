import { Component, OnInit } from '@angular/core';
import { Campus, CampusApiResponse } from '../../interfaces/Campus';
import { CampusService } from '../../services/campus.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-campus-display',
  templateUrl: './campus-display.component.html',
  styleUrls: ['./campus-display.component.scss']
})
export class CampusDisplayComponent implements OnInit {

  campuses: Campus[] = [];
  totalPages: number = 0;
  currentPage: number = 0;
  pageSize: number = 6;
  totalElements: number = 0;

  // --- Modal State Variables ---
  showDeleteModal: boolean = false;
  showSuccessModal: boolean = false;
  campusToDelete: Campus | null = null;

  constructor(
    private campusService: CampusService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadCampuses(this.currentPage);
  }

  loadCampuses(page: number) {
    this.campusService.getCampusPaginated(page, this.pageSize).subscribe({
      next: (response: CampusApiResponse) => {
        this.campuses = response.content;
        this.currentPage = response.number;
        this.totalPages = response.totalPages;
        this.totalElements = response.totalElements;
      },
      error: (err) => {
        console.error('Error fetching campuses', err);
      }
    });
  }

  onPageChange(page: number) {
    if (page >= 0 && page < this.totalPages) {
      this.loadCampuses(page);
    }
  }

  getPagesArray(): number[] {
    return Array.from({ length: this.totalPages }, (_, i) => i);
  }

  // --- Edit Logic ---
  onEdit(campus: Campus) {
    let id = campus.id;
    this.router.navigate(['portal','admin',id]);
  }

  // --- Delete Modal Logic ---
  promptDelete(campus: Campus) {
    this.campusToDelete = campus;
    this.showDeleteModal = true;
  }

  cancelDelete() {
    this.showDeleteModal = false;
    this.campusToDelete = null;
  }

  confirmDelete() {
    if (this.campusToDelete) {
      this.campusService.deleteACampus(this.campusToDelete.id).subscribe({
        next: (data) => {
          // 1. Reset to the first page and fetch fresh data
          this.currentPage = 0;
          this.loadCampuses(this.currentPage);

          // 2. Manage Modal UI State
          this.showDeleteModal = false;
          this.showSuccessModal = true;
          this.campusToDelete = null;
        },
        error: (err) => {
          console.error('Failed to delete campus:', err);

          // Fallback UI if the API fails
          this.showDeleteModal = false;
          this.campusToDelete = null;
          alert('Error: Could not delete the campus. Please try again later.');
        }
      });
    }
  }

  closeSuccessModal() {
    this.showSuccessModal = false;
    // Optional: this.loadCampuses(this.currentPage); // refresh the table
  }

}