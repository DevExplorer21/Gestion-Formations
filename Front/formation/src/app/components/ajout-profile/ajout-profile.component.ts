import { Component, Inject, OnInit,ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AstMemoryEfficientTransformer } from '@angular/compiler';
import {MatDialog} from '@angular/material/dialog';
//import { DomainComponent } from './components/domain/domain.component';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { DomainComponent } from '../domain/domain.component';
import { ProfileComponent } from '../profile/profile.component';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: 'app-ajout-profile',
  templateUrl: './ajout-profile.component.html',
  styleUrls: ['./ajout-profile.component.css']
})
export class AjoutProfileComponent implements OnInit {

  profileForm !: FormGroup;
  actionsBtn: String="Ajouter"
  constructor(private formBuilder: FormBuilder,
    private dialogref:MatDialogRef<ProfileComponent>,
    @Inject(MAT_DIALOG_DATA) public editData:any,  
    private profileService: ProfileService) { }



  ngOnInit(): void {
    this.profileForm=this.formBuilder.group({
      libelle :['', Validators.required]
    });
    console.log(this.editData);
    if(this.editData)
    { this.actionsBtn="Modifier";
      this.profileForm.controls['libelle'].setValue(this.editData.libelle);
    }
  }

  addProfile(){
    console.log(this.profileForm.value);
    if(!this.editData){
      if(this.profileForm.valid){
        this.profileService.create(this.profileForm.value)
        .subscribe({
          next:(res)=>{
            alert("Profile est ajouté avec succès");
            this.profileForm.reset();
            this.dialogref.close('save');
          },
          error:()=>{
            alert("Erreur dans la création de Profile")
          }
        })
      }
    }else{
     this.updateProfile();
    }
    }

    updateProfile(){
      this.profileService.update(this.editData.id, this.profileForm.value)
      .subscribe({
        next:(res)=>{
          alert("Profile est modifiée avec succès");
          this.profileForm.reset();
          this.dialogref.close('update');
        },
        error:()=>{
          alert("Erreur dans la modification de Profile");
        }
      })
    }


}
