import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { DomaineService } from 'src/app/services/domaine.service';
import { FormationService } from 'src/app/services/formation.service';
import { FormationComponent } from '../formation/formation.component';

@Component({
  selector: 'app-ajout-formation',
  templateUrl: './ajout-formation.component.html',
  styleUrls: ['./ajout-formation.component.css']
})
export class AjoutFormationComponent implements OnInit {

  formationForm !: FormGroup;
  actionsBtn: String = "Ajouter";


  domaine_data: any;
  domaines: any;
  constructor(private formBuilder: FormBuilder,
    private dialogref: MatDialogRef<FormationComponent>,
    @Inject(MAT_DIALOG_DATA) public editData: any,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private formationService: FormationService,
    private domaineService: DomaineService) {
    this.domaine_data = { ...data };
    this.domaineService.getAll().subscribe((data: any) => {
      this.domaines = data;
      this.domaine_data.domain = data;
      console.log("ssss"+this.domaines);
    })

  }
  ngOnInit(): void {

    this.formationForm = this.formBuilder.group({
      titre: ['', Validators.required],
      annee: ['', Validators.required],
      duree: ['', Validators.required],
      budget: ['', Validators.required],
      nb_session: ['', Validators.required],
      type: ['', Validators.required],
      domain: ['', Validators.required]

    });
    console.log(this.formationForm);
    console.log(this.editData);
    if (this.editData) {
      console.log('domaine', this.editData.domain.libelle);
      this.actionsBtn = "Modifier";
      this.formationForm.controls['titre'].setValue(this.editData.titre);
      this.formationForm.controls['annee'].setValue(this.editData.annee);
      this.formationForm.controls['duree'].setValue(this.editData.duree);
      this.formationForm.controls['budget'].setValue(this.editData.budget);
      this.formationForm.controls['nb_session'].setValue(this.editData.nb_session)
      this.formationForm.controls['type'].setValue(this.editData.type);
      this.formationForm.controls['domain'].setValue(this.editData.domain);
    }
  }

  addFormation() {
    console.log(this.formationForm.value);
    console.log(this.formationForm.value.nom);
    if (!this.editData) {
      if (this.formationForm.valid) {
        console.log(this.formationForm.valid);
        ///////////////////
        let dataa = {
          "titre": this.formationForm.value.titre,
          "annee": this.formationForm.value.annee,
          "duree": this.formationForm.value.duree,
          "budget": this.formationForm.value.budget,
          "nb_session": this.formationForm.value.nb_session,
          "type": this.formationForm.value.type,
          "domain": {
            "id": this.formationForm.value.domain
          }
        };
        //////////////////
        this.formationService.create(dataa)
          .subscribe({
            next: (res) => {
              alert("Formation est ajouté avec succès");
              this.formationForm.reset();
              this.dialogref.close('save');
            },
            error: () => {
              alert("Erreur dans la création de Formation")
            }
          })
      }
    } else {
      this.updateFormation();
    }
  }

  updateFormation() {
    let dataa = {
      "titre": this.formationForm.value.titre,
      "annee": this.formationForm.value.annee,
      "duree": this.formationForm.value.duree,
      "budget": this.formationForm.value.budget,
      "nb_session": this.formationForm.value.nb_session,
      "type": this.formationForm.value.type,
      "domain": {
        "id": this.formationForm.value.domain
      }
    };
    this.formationService.update(this.editData.id, dataa)
      .subscribe({
        next: (res) => {
          alert("Formation est modifiée avec succès");
          this.formationForm.reset();
          this.dialogref.close('update');
        },
        error: () => {
          alert("Erreur dans la modification de formation");
        }
      })
  }

}
