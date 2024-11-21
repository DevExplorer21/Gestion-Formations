import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer, identifierName } from '@angular/compiler';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { ParticipantComponent } from '../participant/participant.component';
import { PaysComponent } from '../pays/pays.component';
import { AjoutPaysComponent } from '../ajout-pays/ajout-pays.component';
import { PaysService } from 'src/app/services/pays.service';
import { ParticipantService } from 'src/app/services/participant.service';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: 'app-ajout-participant',
  templateUrl: './ajout-participant.component.html',
  styleUrls: ['./ajout-participant.component.css']
})
export class AjoutParticipantComponent implements OnInit {
  participantForm !: FormGroup;
  actionsBtn: String = "Ajouter";
  //org!:paysComponent;

  pays_data: any;
  payss: any;

  profil_data: any;
  profils: any;
  constructor(private formBuilder: FormBuilder,
    private dialogref: MatDialogRef<ParticipantComponent>,
    @Inject(MAT_DIALOG_DATA) public editData: any,
    @Inject(MAT_DIALOG_DATA) public data: any,
    @Inject(MAT_DIALOG_DATA) public data1: any,
    private participantService: ParticipantService,
    private profileService: ProfileService,
    private paysService: PaysService) {

    this.pays_data = { ...data };
    this.paysService.getAll().subscribe((data: any) => {
      this.payss = data;
      //console.log(this.pays_data.pays);
      this.pays_data.pays = data[0];
      console.log(this.payss);
    }),

      this.profil_data = { ...data1 };
    this.profileService.getAll().subscribe((data1: any) => {
      this.profils = data1;
      //console.log(this.pays_data.pays);
      this.profil_data.profil = data1[0];
      console.log(this.profils);
    })
    //api.getpays();
  }
  ngOnInit(): void {

    //console.log(this.org.getAllpayss());
    this.participantForm = this.formBuilder.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', Validators.required],
      telephone: ['', Validators.required],
      type: ['', Validators.required],
      pays: ['', Validators.required],
      profil: ['', Validators.required]


    });
    console.log(this.participantForm);
    console.log(this.editData);
    if (this.editData) {
      this.actionsBtn = "Modifier";
      this.participantForm.controls['nom'].setValue(this.editData.nom);
      this.participantForm.controls['prenom'].setValue(this.editData.prenom);
      this.participantForm.controls['email'].setValue(this.editData.email);
      this.participantForm.controls['telephone'].setValue(this.editData.telephone);
      this.participantForm.controls['type'].setValue(this.editData.type);
      this.participantForm.controls['pays'].setValue(this.editData.pays);
      this.participantForm.controls['profil'].setValue(this.editData.profil);
    }
  }

  addParticipant() {
    console.log(this.participantForm.value);
    console.log(this.participantForm.value.nom);
    if (!this.editData) {
      if (this.participantForm.valid) {
        console.log(this.participantForm.valid);
        ///////////////////
        let dataa = {
          "nom": this.participantForm.value.nom,
          "prenom": this.participantForm.value.prenom,
          "email": this.participantForm.value.email,
          "telephone": this.participantForm.value.telephone,
          "type": this.participantForm.value.type,
          "pays": {
            "id": this.participantForm.value.pays
          }, "profil": {
            "id": this.participantForm.value.profil
          }
        };
        //////////////////
        //console.log('participant Form',this.participantForm.value.pays.id);
        this.participantService.create(dataa)
          .subscribe({
            next: (res) => {
              alert("Participant est ajouté avec succès");
              this.participantForm.reset();
              this.dialogref.close('save');
            },
            error: () => {
              alert("Erreur dans la création de participant")
            }
          })
      }
    } else {
      this.updateParticipant();
    }
  }

  updateParticipant() {
    let dataa = {
      "nom": this.participantForm.value.nom,
      "prenom": this.participantForm.value.prenom,
      "email": this.participantForm.value.email,
      "telephone": this.participantForm.value.telephone,
      "type": this.participantForm.value.type,
      "pays": {
        "id": this.participantForm.value.pays
      }, "profil": {
        "id": this.participantForm.value.profil
      }
    };
    this.participantService.update(this.editData.id, dataa)
      .subscribe({
        next: (res) => {
          alert("Participant est modifiée avec succès");
          this.participantForm.reset();
          this.dialogref.close('update');
        },
        error: () => {
          alert("Erreur dans la modification du participant");
        }
      })
  }


}
