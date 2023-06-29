import { Component } from '@angular/core';
import { SlideInterface } from 'src/app/imageSlider/types/slide.interface';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  slides: SlideInterface[] = [
    { url: '/assets/images/pet13.png', title: 'beach' },
    { url: '/assets/images/pet14.png', title: 'beach' },
    { url: '/assets/images/pet15.png', title: 'beach' },
    { url: '/assets/images/pet16.png', title: 'beach' },
    { url: '/assets/images/pet17.png', title: 'beach' },
    { url: '/assets/images/pet18.png', title: 'beach' },
    { url: '/assets/images/pet19.png', title: 'beach' },
    { url: '/assets/images/pet20.png', title: 'beach' },
    { url: '/assets/images/pet21.png', title: 'beach' },
    { url: '/assets/images/pet22.png', title: 'beach' },
    { url: '/assets/images/pet23.png', title: 'beach' },
    { url: '/assets/images/pet24.png', title: 'beach' },
  ];

  divWidthPercentage = 80;
  divHeightPercentage = 40;


}
