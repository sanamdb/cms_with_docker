import { Component, OnInit } from '@angular/core';
import { DashboardMetrics, DashboardService }  from '../../services/dashboard.service'
import { from } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  dashboardData!: DashboardMetrics;
  isLoading = true;

  constructor(
    private dashboardService: DashboardService
  ) { }

  ngOnInit(): void {
    this.dashboardService.getDashboardData().subscribe({
      next: (data) => {
        this.dashboardData = data;
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error fetching dashboard data', err);
        this.isLoading = false;
      }
    });
  }
}