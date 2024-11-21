import { Component, OnInit,ViewChild } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { DomainComponent } from './components/domain/domain.component';
import { OrganismeComponent } from './components/organisme/organisme.component';
import { FormateurComponent } from './components/formateur/formateur.component';
import { PaysComponent } from './components/pays/pays.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ParticipantComponent } from './components/participant/participant.component';
import { SessionComponent } from './components/session/session.component';
import { FormationComponent } from './components/formation/formation.component';
import { TokenStorageService } from './services/token-storage.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  private roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;
  title = 'frontfinal';
  showFiller = false;
  //displayedColumns: string[] = ['id', 'libelle', 'actions'];
  dataSource!: MatTableDataSource<any>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private tokenStorageService: TokenStorageService, private router : Router, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');
      this.username = this.tokenStorageService.getUser().username;
    }
  }
  openDomain() {
    this.dialog.open(DomainComponent, {
      width:'50%'
    }) 
  }

  openOrganisme() {
    this.dialog.open(OrganismeComponent, {
      width:'50%'
    })
  }

  openFormateur() {
    this.dialog.open(FormateurComponent, {
      width:'50%'
    })
  }

  openFormation() {
    this.dialog.open(FormationComponent, {
      width:'50%'
    })
  }

  openSession() {
    this.dialog.open(SessionComponent, {
      width:'50%'
    })
  }

  openParticipant() {
    this.dialog.open(ParticipantComponent, {
      width:'50%'
    })
  }

  openProfile() {
    this.dialog.open(ProfileComponent, {
      width:'50%'
    })
  }

  openPays() {
    this.dialog.open(PaysComponent, {
      width:'50%'
    })
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

  goToHome(){
    this.router.navigateByUrl('/home');
  }

  goToLogin(){
    this.router.navigateByUrl('/login');
  }
  
  goToRegister(){
    this.router.navigateByUrl('/register');
  }

  goToDomaines(){
    this.router.navigateByUrl('/domaines');
  }
}


