import { Injectable } from '@angular/core';
import { BannerSlide } from '../interfaces/BannerSlide';

@Injectable({
  providedIn: 'root'
})
export class BannerSlideService {

  banner: BannerSlide[] = [];

  constructor() { }

  getBannerSlider(): BannerSlide[] {
    return this.banner = [
      {
        image: 'assets/images/banner1.jpg',
        title: 'Welcome to Global University Portal',
        subtitle: 'Empowering future leaders through excellence in education and innovation.'
      },
      {
        image: 'assets/images/banner2.jpg',
        title: 'State-of-the-Art Research Facilities',
        subtitle: 'Explore our advanced laboratories and collaborative tech hubs.'
      },
      {
        image: 'assets/images/banner3.jpg',
        title: 'Campus Life & Cultural Events',
        subtitle: 'A vibrant community fostering creativity, teamwork, and holistic growth.'
      },
      {
        image: 'assets/images/banner4.jpg',
        title: 'Global Industry Partnerships',
        subtitle: 'Bridging academia with top-tier corporate leaders for unmatched placements.'
      },
      {
        image: 'assets/images/banner5.jpg',
        title: 'Admissions Open for 2026-2027',
        subtitle: 'Take the first step towards a brilliant career. Apply online today.'
      },
      {
        image: 'assets/images/banner6.jpg',
        title: 'Excellence in Sports & Athletics',
        subtitle: 'World-class playgrounds and coaching arenas to build physical resilience.'
      }
    ];
  }
}
