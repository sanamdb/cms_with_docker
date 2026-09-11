import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../interfaces/User';
import { OTP } from '../../interfaces/OTP';
import { UserProfile } from '../../interfaces/UserProfile';
import { SignupService } from '../../services/signup.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  constructor(
    private signUpService: SignupService,
    private router: Router
  ) { }

  activeTab: string = "signUp";
  selectedFile: File | null = null;
  previewUrl: string | ArrayBuffer | null = null; // Holds the base64 image preview
  isLoading: boolean = false; // Added: Controls the loader state

  signUp: User = { username: '', password: '', email: '', phoneNo: '' };
  otp: OTP = { username: '', otp: '' };
  userProfile: UserProfile = {
    firstName: '', lastName: '', dob: '', email: '', alternateEmail: '',
    phoneNo: '', alternatePhoneNo: '', fullAddress: '', city: '', pincode: '', state: ''
  };

  globalUsername: string = '';
  errorMessage: string = '';

  ngOnInit(): void {}

  // Helper to show modern floating error
  showError(msg: string) {
    this.errorMessage = msg;
    setTimeout(() => this.errorMessage = '', 5000); // Auto-hide after 5s
  }

  registerUser(user: NgForm) {
    if (user.invalid) return; // Stop if form fails validation

    this.isLoading = true; // Added: Start loader

    this.signUpService.registerUser(user.value).subscribe({
      next: (response) => {
        this.isLoading = false; // Added: Stop loader on success
        this.globalUsername = response.username;
        this.activeTab = 'otp';
      },
      error: (err) => {
        this.isLoading = false; // Added: Stop loader on error
        this.showError(err.error?.errorMessage || 'Registration failed');
      }
    });
  }

  verifyOtp(otpFrm: NgForm) {
    if (otpFrm.invalid) return;

    this.signUpService.verifyOtp(otpFrm.value, this.globalUsername).subscribe({
      next: (response) => {
        if (response.status === 'VERIFIED') this.activeTab = 'profile';
        else this.showError(response.message);
      }, 
      error: (err) => this.showError(err.error?.errorMessage || 'OTP verification failed')
    });
  }

  saveProfile(profile: NgForm) {
    if (profile.invalid) return;

    this.signUpService.createUserProfile(profile.value, this.globalUsername).subscribe({
      next: () => this.activeTab = 'profilePic',
      error: (err) => this.showError(err.error?.errorMessage || 'Profile save failed')
    });
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];

      // Generate Image Preview using FileReader
      const reader = new FileReader();
      reader.onload = () => this.previewUrl = reader.result;
      reader.readAsDataURL(this.selectedFile);
    }
  }

  onUpload(): void {
    if (!this.selectedFile) {
      this.showError('Kindly Select Profile Picture To Upload');
      return;
    }
    const formData = new FormData();
    formData.append('file', this.selectedFile);

    this.signUpService.uploadProfilePicture(formData, this.globalUsername).subscribe({
      next: () => this.activeTab = 'finish',
      error: (err) => this.showError(err.error?.errorMessage || 'Upload failed')
    });
  }

  loginWithCredential() {
    this.router.navigate(['login']);
  }
}