import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { DomainComponent } from '../domain/domain.component';
import { FormateurComponent } from '../formateur/formateur.component';
import { FormationComponent } from '../formation/formation.component';
import { OrganismeComponent } from '../organisme/organisme.component';
import { ParticipantComponent } from '../participant/participant.component';
import { PaysComponent } from '../pays/pays.component';
import { ProfileComponent } from '../profile/profile.component';
import { SessionComponent } from '../session/session.component';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  currentUser: any;
  isAdmin = false;
  constructor(private token: TokenStorageService, private dialog: MatDialog) {}
  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.isAdmin = this.token.getUser().roles.includes('ROLE_ADMIN');
  }
  toDomain() {
    this.dialog.open(DomainComponent, {
      width:'50%'
    })
  }
  toOrganisme() {
    this.dialog.open(OrganismeComponent, {
      width:'50%'
    })
  }
  toPays() {
    this.dialog.open(PaysComponent, {
      width:'50%'
    })
  }
  toProfil() {
    this.dialog.open(ProfileComponent, {
      width:'50%'
    })
  }
  toParticipant() {
    this.dialog.open(ParticipantComponent, {
      width:'50%'
    })
  }
  toFormateur() {
    this.dialog.open(FormateurComponent, {
      width:'50%'
    })
  }
  toSession() {
    this.dialog.open(SessionComponent, {
      width:'50%'
    })
  }
  toFormation() {
    this.dialog.open(FormationComponent, {
      width:'50%'
    })
  }
  
  

}
