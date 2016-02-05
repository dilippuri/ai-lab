clear all;
close all;
data = load('iris_data.txt'); 					%load data into data matrix

[iris_row iris_col]=size(data); 				%size of input data

num_of_iteration=10;						%number of iteration
train=iris_row*(.7);						%train ratio
test=iris_row*(.3);						%test ratio

w=rand(1,iris_col);						%initialize random weight
w_update=w;
err_in(1) = 0;err_out(1) = 0;
for n=1:num_of_iteration					%loop for epochs				
	ein=0;
for i=1:train					 		%training data
	x=[1 data(i,1:4)];
	h=sign(w*x');
	y=data(i,5);
	if(y!=h)	
		w_update = w_update + y*x;			%update weight vector
		ein++;
	endif
endfor
err_in(n)=ein/train;
	eout=0;					
for i=1:test							%testing on data

	x=[1 data(i,1:4)];
	h=sign(w*x');
	y=data(i,5);
	if(y!=h)
		eout++;
	endif
endfor
err_out(n)=eout/test;

	if(n>1 && err_in(n)<=err_in(n-1))			%update weight if get less error --pocket
		w=w_update;
	endif

endfor

plot(err_in,"-;Ein;","color",'r');				%plotting graph
hold on;
plot(err_out,"d","color",'k');
hold on;
plot(err_in,"*","color",'r',err_out,":;Eout;","color",'k');
xlabel("Epochs");
ylabel("Ein/Eout");
title("Evoluation of Ein and Eout");
axis([0 10 0 .8]);
print -dpng pocket.png;
