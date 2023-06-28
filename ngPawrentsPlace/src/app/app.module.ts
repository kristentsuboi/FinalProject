import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { PetListComponent } from './components/pet-list/pet-list.component';
import { HomeComponent } from './components/home/home.component';
import { ClientListComponent } from './components/client-list/client-list.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AboutComponent } from './components/about/about.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { NgbAccordionModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from './services/auth.service';
import { AccountComponent } from './components/account/account.component';
import { PetComponent } from './components/pet/pet.component';

import { BusinessComponent } from './components/business/business.component';
import { ChatComponent } from './components/chat/chat.component';

import { DatePipe, TitleCasePipe } from '@angular/common';

import { FooterComponent } from './components/footer/footer.component';
import { LastestPostComponent } from './components/lastest-post/lastest-post.component';
import { TeamComponent } from './components/team/team.component';
import { ContactComponent } from './components/contact/contact.component';
import { ForumComponent } from './components/forum/forum.component';
import { DueDatePipe } from './pipes/due-date.pipe';
import { MedicineDuePipe } from './pipes/medicine-due.pipe';

@NgModule({
  declarations: [
    AppComponent,
    PetListComponent,
    HomeComponent,
    ClientListComponent,
    NavigationComponent,
    RegisterComponent,
    LoginComponent,
    LogoutComponent,
    AboutComponent,
    NotFoundComponent,
    AccountComponent,
    PetComponent,

    BusinessComponent,
    ChatComponent,
    FooterComponent,
    LastestPostComponent,
    TeamComponent,
    ContactComponent,
    ForumComponent,
    DueDatePipe,
    MedicineDuePipe,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,

    NgbAccordionModule,
    DatePipe
  ],
  providers: [AuthService, DueDatePipe],
  bootstrap: [AppComponent],
})
export class AppModule {}
