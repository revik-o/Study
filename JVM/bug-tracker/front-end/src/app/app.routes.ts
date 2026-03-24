import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { 
    path: 'login', 
    loadComponent: () => import('./features/auth/pages/login-page/login-page.component').then(m => m.LoginPageComponent) 
  },
  { 
    path: 'register', 
    loadComponent: () => import('./features/auth/pages/registration-page/registration-page.component').then(m => m.RegistrationPageComponent) 
  },
  {
    path: 'app',
    loadComponent: () => import('./features/main/layout/main-layout/main-layout.component').then(m => m.MainLayoutComponent),
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { 
        path: 'dashboard', 
        loadComponent: () => import('./features/dashboard/pages/dashboard-page/dashboard-page.component').then(m => m.DashboardPageComponent) 
      },
      { 
        path: 'users', 
        loadComponent: () => import('./features/users/pages/user-management-page/user-management-page.component').then(m => m.UserManagementPageComponent) 
      }
    ]
  },
  { path: '**', redirectTo: 'login' }
];
