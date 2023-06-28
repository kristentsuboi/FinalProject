import { Component } from '@angular/core';
import { SlideInterface } from 'src/app/imageSlider/types/slide.interface';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  slides: SlideInterface[] = [
    { url: '/assets/images/image-1.jpeg', title: 'beach' },
    { url: '/assets/images/image-2.jpeg', title: 'boat' },
    { url: '/assets/images/image-3.jpeg', title: 'forest' },
    { url: '/assets/images/image-4.jpeg', title: 'city' },
    { url: '/assets/images/image-5.jpeg', title: 'italy' },
  ];

}
