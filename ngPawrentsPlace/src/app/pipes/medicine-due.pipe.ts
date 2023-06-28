import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'medicineDue'
})
export class MedicineDuePipe implements PipeTransform {



    transform(lastDateStr: string, frequency: string): Date{
      let lastDate = new Date(lastDateStr);
      let dueDate = new Date();
      if(frequency === "twice daily"){
        dueDate.setDate(lastDate.getDate());
      }
      if(frequency === "daily"){
        dueDate.setDate(lastDate.getDate() + 1);
      }
      if(frequency === "alternate days"){
        dueDate.setDate(lastDate.getDate() + 2);
      }
      if(frequency === "weekly"){
        dueDate.setDate(lastDate.getDate() + 7);
      }
      if(frequency === "monthly"){
        dueDate.setDate(lastDate.getDate() + 30);
      }
      if(frequency === "annually"){
        dueDate.setDate(lastDate.getDate() + 365);
      }

      return dueDate;
    }


}
