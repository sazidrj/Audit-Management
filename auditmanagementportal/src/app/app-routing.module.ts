import { QuestionsComponent } from './pages/questions/questions.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { AuthGuard } from './services/auth.guard';
import { StatusComponent } from './pages/status/status.component';
import { TokenExpiredPageComponent } from './pages/token-expired-page/token-expired-page.component';
import { InteralServerErrorComponent } from './pages/interal-server-error/interal-server-error.component';
import { ForbiddenComponent } from './pages/forbidden/forbidden.component';
import { AuthFailedComponent } from './pages/auth-failed/auth-failed.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
    pathMatch: 'full',
   
  },
  {
    path: 'home',
    component: HomeComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'questions',
    component: QuestionsComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },{
    path: 'status',
    component: StatusComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'token-expired',
    component: TokenExpiredPageComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },

  {
    path: 'internalservererror',
    component: InteralServerErrorComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'forbidden',
    component: ForbiddenComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  },
  {
    path: 'auth-failed',
    component: AuthFailedComponent,
    pathMatch: 'full',
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
