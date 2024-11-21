import { Component, Inject, OnInit,ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormateurService } from 'src/app/services/formateur.service';
import { FormationService } from 'src/app/services/formation.service';
import { OrganismeService } from 'src/app/services/organisme.service';
import { SessionService } from 'src/app/services/session.service';
import { FormationComponent } from '../formation/formation.component';
import { SessionComponent } from '../session/session.component';

@Component({
  selector: 'app-ajout-session',
  templateUrl: './ajout-session.component.html',
  styleUrls: ['./ajout-session.component.css']
})
export class AjoutSessionComponent implements OnInit {

  sessionForm !: FormGroup;
  actionsBtn: String="Ajouter";
  
  formateur_data:any;
  formateurs:any;

  formation_data:any;
  formations:any;

  organisme_data:any;
  organismes:any;

  constructor(private formBuilder: FormBuilder,
    private dialogref:MatDialogRef<SessionComponent>,
    @Inject(MAT_DIALOG_DATA) public editData:any,
    @Inject(MAT_DIALOG_DATA) public data1:any,
    @Inject(MAT_DIALOG_DATA) public data2:any,
    @Inject(MAT_DIALOG_DATA) public data3:any,  
    private organismeService: OrganismeService, 
    private formationService: FormationService, 
    private formateurService: FormateurService,
    private sessionService: SessionService) 
    {
      this.organisme_data = {...data1};
     this.organismeService.getAll().subscribe((data1: any) => {
        this.organismes = data1 ;
        this.organisme_data.organisme = data1[0];
        console.log(this.organismes);
     })

     this.formation_data = {...data2};
     this.formationService.getAll().subscribe((data2: any) => {
        this.formations = data2 ;
        this.formation_data.formation = data2[0];
        console.log(this.formations);
     }),

     this.formateur_data = {...data3};
     this.formateurService.getAll().subscribe((data3: any) => {
        this.formateurs = data3 ;
        this.formateur_data.formateur = data3[0];
        console.log(this.formateurs);
     })
   
    }
  ngOnInit(): void {
    
    this.sessionForm=this.formBuilder.group({
      date_debut:['', Validators.required],
      date_fin :['', Validators.required],
      lieu:['', Validators.required],
      nbr_participants :['', Validators.required],
      formateur :['', Validators.required],
      organisme :['', Validators.required],
      formations : ['', Validators.required]
  
    });
    console.log(this.sessionForm);
    console.log(this.editData);
    if(this.editData)
    { //console.log('domaine',this.editData.domain.libelle);
      this.actionsBtn="Modifier";
      this.sessionForm.controls['date_debut'].setValue(this.editData.date_debut);
      this.sessionForm.controls['date_fin'].setValue(this.editData.date_fin);
      this.sessionForm.controls['lieu'].setValue(this.editData.lieu);
      this.sessionForm.controls['nbr_participants'].setValue(this.editData.nbr_participants);
      this.sessionForm.controls['formateur'].setValue(this.editData.formateur)
      this.sessionForm.controls['organisme'].setValue(this.editData.organisme);
      this.sessionForm.controls['formations'].setValue(this.editData.formations);
    }
  }

  addSession(){
    console.log(this.sessionForm.value);
    //console.log(this.sessionForm.value.nom);
    if(!this.editData){
      if(this.sessionForm.valid){
        console.log(this.sessionForm.valid);
        ///////////////////
        let dataa={
          "dateDebut": this.sessionForm.value.date_debut,
          "dateFin": this.sessionForm.value.date_fin,
          "lieu": this.sessionForm.value.lieu,
          "nbrParticipants": this.sessionForm.value.nbr_participants,
          "formateur": {
            "id": this.sessionForm.value.formateur },
          "organisme": {
            "id": this.sessionForm.value.organisme },
          "formations": {
            "id": this.sessionForm.value.formations }
      };
        //////////////////
        this.sessionService.create(dataa)
        .subscribe({
          next:(res)=>{
            alert("Session est ajouté avec succès");
            this.sessionForm.reset();
            this.dialogref.close('save');
          },
          error:()=>{
            alert("Erreur dans la création de Session")
          }
        })
      }
    }else{
     this.updateSession();
    }
    }

    updateSession(){
      let dataa={
        "dateDebut": this.sessionForm.value.date_debut,
        "dateFin": this.sessionForm.value.date_fin,
        "lieu": this.sessionForm.value.lieu,
        "nbrParticipants": this.sessionForm.value.nbr_participants,
        "formateur": {
          "id": this.sessionForm.value.formateur },
        "organisme": {
          "id": this.sessionForm.value.organisme },
        "formations": {
          "id": this.sessionForm.value.formations }
    };
      this.sessionService.update(this.editData.id, dataa)
      .subscribe({
        next:(res)=>{
          alert("Session est modifiée avec succès");
          this.sessionForm.reset();
          this.dialogref.close('update');
        },
        error:()=>{
          alert("Erreur dans la modification de session");
        }
      })
    }


}
