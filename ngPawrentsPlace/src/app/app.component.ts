import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { SlideInterface } from './imageSlider/types/slide.interface';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  slides: SlideInterface[] = [
    { url: '/assets/images/image-1.jpeg', title: 'beach' },
    { url: '/assets/images/image-2.jpeg', title: 'boat' },
    { url: '/assets/images/image-3.jpeg', title: 'forest' },
    { url: '/assets/images/image-4.jpeg', title: 'city' },
    { url: '/assets/images/image-5.jpeg', title: 'italy' },
  ];

  title = 'ngPawrentsPlace';

  constructor(private titleService:Title) {
    this.titleService.setTitle("Pawrents Place");
  }


}
