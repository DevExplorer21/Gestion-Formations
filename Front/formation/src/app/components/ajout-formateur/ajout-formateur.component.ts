import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer, identifierName } from '@angular/compiler';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { FormateurComponent } from '../formateur/formateur.component';
import { OrganismeComponent } from '../organisme/organisme.component';
import { AjoutOrganismeComponent } from '../ajout-organisme/ajout-organisme.component';
import { OrganismeService } from 'src/app/services/organisme.service';
import { FormateurService } from 'src/app/services/formateur.service';

@Component({
  selector: 'app-ajout-formateur',
  templateUrl: './ajout-formateur.component.html',
  styleUrls: ['./ajout-formateur.component.css']
})
export class AjoutFormateurComponent implements OnInit {
  formateurForm !: FormGroup;
  actionsBtn: String = "Ajouter";
  organisme_data: any;
  organismes: any;
  constructor(private formBuilder: FormBuilder,
    private dialogref: MatDialogRef<FormateurComponent>,
    @Inject(MAT_DIALOG_DATA) public editData: any,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private organismeService: OrganismeService,
    private formateurService: FormateurService) {
    this.organisme_data = { ...data };
    this.organismeService.getAll().subscribe((data: any) => {
      this.organismes = data;
      this.organisme_data.organisme = data[0];
      console.log(this.organismes);
    })
  }

  ngOnInit(): void {
    this.formateurForm = this.formBuilder.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', Validators.required],
      telephone: ['', Validators.required],
      type: ['', Validators.required],
      organisme: ['', Validators.required]
    });
    console.log(this.formateurForm);
    console.log(this.editData);
    if (this.editData) {
      console.log('organisme', this.editData.organisme.libelle);
      this.actionsBtn = "Modifier";
      this.formateurForm.controls['nom'].setValue(this.editData.nom);
      this.formateurForm.controls['prenom'].setValue(this.editData.prenom);
      this.formateurForm.controls['email'].setValue(this.editData.email);
      this.formateurForm.controls['telephone'].setValue(this.editData.telephone);
      this.formateurForm.controls['type'].setValue(this.editData.type);
      this.formateurForm.controls['organisme'].setValue(this.editData.organisme);
    }
  }

  addFormateur() {
    console.log(this.formateurForm.value);
    console.log(this.formateurForm.value.nom);
    if (!this.editData) {
      if (this.formateurForm.valid) {
        console.log(this.formateurForm.valid);
        ///////////////////
        let dataa = {
          "nom": this.formateurForm.value.nom,
          "prenom": this.formateurForm.value.prenom,
          "email": this.formateurForm.value.email,
          "telephone": this.formateurForm.value.telephone,
          "type": this.formateurForm.value.type,
          "organisme": {
            "id": this.formateurForm.value.organisme
          }
        };
        this.formateurService.create(dataa)
          .subscribe({
            next: (res) => {
              alert("Formateur est ajouté avec succès");
              this.formateurForm.reset();
              this.dialogref.close('save');
            },
            error: () => {
              alert("Erreur dans la création de Formateur")
            }
          })
      }
    } else {
      this.updateFormateur();
    }
  }

  updateFormateur() {
    let dataa = {
      "nom": this.formateurForm.value.nom,
      "prenom": this.formateurForm.value.prenom,
      "email": this.formateurForm.value.email,
      "telephone": this.formateurForm.value.telephone,
      "type": this.formateurForm.value.type,
      "organisme": {
        "id": this.formateurForm.value.organisme
      }
    };
    this.formateurService.update(this.editData.id, dataa)
      .subscribe({
        next: (res) => {
          alert("Formateur est modifiée avec succès");
          this.formateurForm.reset();
          this.dialogref.close('update');
        },
        error: () => {
          alert("Erreur dans la modification de formateur");
        }
      })
  }

}
