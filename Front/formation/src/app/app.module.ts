import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { BoardAdminComponent } from './components/board-admin/board-admin.component';
import { BoardModeratorComponent } from './components/board-moderator/board-moderator.component';
import { BoardUserComponent } from './components/board-user/board-user.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AddDomaineComponent } from './components/add-domaine/add-domaine.component';
import { DetailsDomaineComponent } from './components/details-domaine/details-domaine.component';
import { ListDomaineComponent } from './components/list-domaine/list-domaine.component';
import { authInterceptorProviders } from './helpers/auth.interceptor';
import { FormateurComponent } from './components/formateur/formateur.component';
import { FormationComponent } from './components/formation/formation.component';
import { OrganismeComponent } from './components/organisme/organisme.component';
import { ParticipantComponent } from './components/participant/participant.component';
import { PaysComponent } from './components/pays/pays.component';
import { SessionComponent } from './components/session/session.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialogModule } from '@angular/material/dialog';
import { DomainComponent } from './components/domain/domain.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker'
import { MatNativeDateModule } from '@angular/material/core'
import { HttpClient} from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { AjoutFormationComponent } from './components/ajout-formation/ajout-formation.component';
import { AjoutOrganismeComponent } from './components/ajout-organisme/ajout-organisme.component';
import { AjoutParticipantComponent } from './components/ajout-participant/ajout-participant.component';
import { AjoutPaysComponent } from './components/ajout-pays/ajout-pays.component';
import { AjoutProfileComponent } from './components/ajout-profile/ajout-profile.component';
import { AjoutSessionComponent } from './components/ajout-session/ajout-session.component';
import { AjoutFormateurComponent } from './components/ajout-formateur/ajout-formateur.component';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardModeratorComponent,
    BoardUserComponent,
    LoginComponent,
    AddDomaineComponent,
    DetailsDomaineComponent,
    ListDomaineComponent,
    DomainComponent,
    FormateurComponent,
    FormationComponent,
    OrganismeComponent,
    ParticipantComponent,
    PaysComponent,
    SessionComponent,
    AjoutFormationComponent,
    AjoutOrganismeComponent,
    AjoutParticipantComponent,
    AjoutPaysComponent,
    AjoutProfileComponent,
    AjoutSessionComponent,
    AjoutFormateurComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatSidenavModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    HttpClientModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
