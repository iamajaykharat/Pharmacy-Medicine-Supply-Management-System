export interface MRSchedule{
    id: number;
    repName: string;
    doctorName: string;
    meetingSlot: string;
    meetingDate: Date;    
    doctorContactNumber: string;
    medicines:[];
    treatingAilment: string
}