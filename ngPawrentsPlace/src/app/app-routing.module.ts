import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { PetListComponent } from './components/pet-list/pet-list.component';
import { RegisterComponent } from './components/register/register.component';
import { AboutComponent } from './components/about/about.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { PetComponent } from './components/pet/pet.component';
import { AccountComponent } from './components/account/account.component';
import { BusinessComponent } from './components/business/business.component';
import { ChatComponent } from './components/chat/chat.component';
import { ContactComponent } from './components/contact/contact.component';
import { ClientListComponent } from './components/client-list/client-list.component';
import { ForumComponent } from './components/forum/forum.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'clients', component: ClientListComponent },
  { path: 'forum', component: ForumComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'account/:id', component: AccountComponent },
  { path: 'business/:id', component: BusinessComponent },
  { path: 'chat', component: ChatComponent },
  { path: 'pets/:id', component: PetComponent },
  { path: 'pets', component: PetListComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: '**', component: NotFoundComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
