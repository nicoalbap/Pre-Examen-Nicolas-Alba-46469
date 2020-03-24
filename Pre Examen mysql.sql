use manufacturing;

Select name from clients 
inner join orders on clients.username = orders.clientusername
where orders.status = 'Done';

Select amount, modeoftransport from invoice
inner join delivery on delivery.orderid = invoice.orderid
inner join clients on delivery.clntusername = clients.username
where clients.name = 'New Client 1';

Select email from employee
left join attendance on employee.username = attendance.empusername
where value is null;

Select * from attendance;
Select * from employee;