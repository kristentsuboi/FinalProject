import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dueDate'
})
export class DueDatePipe implements PipeTransform {

  transform(lastDateStr: string, frequency: string): Date{
    let lastDate = new Date(lastDateStr);
    let dueDate = new Date();
    if(frequency === "initial: 2wks"){
      dueDate.setDate(lastDate.getDate() + 14);
    }
    if(frequency === "initial: 4wks"){
      dueDate.setDate(lastDate.getDate() + 28);
    }
    if(frequency === "semi-annual"){
      dueDate.setDate(lastDate.getDate() + 180);
    }
    if(frequency === "annual"){
      dueDate.setDate(lastDate.getDate() + 365);
    }
    if(frequency === "every 2yrs"){
      dueDate.setDate(lastDate.getDate() + 730);
    }
    if(frequency === "every 3yrs"){
      dueDate.setDate(lastDate.getDate() + 1095);
    }

    return dueDate;
  }



}
