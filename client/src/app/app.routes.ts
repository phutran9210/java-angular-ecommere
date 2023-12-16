import {Routes} from '@angular/router';
import {LandingPageComponent} from "./landing-page/components/landing-page.component";
import {NotFoundComponent} from "./online-store/components/not-found/not-found.component";
import {SignInComponent} from "./online-store/components/auth/sign-in/sign-in.component";
import {RegisterComponent} from "./online-store/components/auth/register/register.component";

export const routes: Routes = [
  {path: '', component: LandingPageComponent},
  {path: 'signin', component: SignInComponent},
  {path: 'register', component: RegisterComponent},
  {path: '**', component: NotFoundComponent}
];
