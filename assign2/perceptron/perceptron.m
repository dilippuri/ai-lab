clear all;
close all;
data = load('iris_data.txt'); %load data into data matrix

[iris_row iris_col]=size(data); %size of input data
datasize=[iris_row iris_col];
save -text perceptron.txt datasize;

train=iris_row*(.7);	%train ratio
test=iris_row*(.3);	%test ratio
train_test_ratio=[train/iris_row test/iris_row];
save -append -text perceptron.txt train_test_ratio;

%w=rand(1,iris_col); %initialize random weight
w=100*ones(1,iris_col-1);
w_update=w;
weight_initial=w_update(1,:);
%dlmwrite("weight.txt", weight_initial,"-append");
save -append -text perceptron.txt weight_initial;
err_in(1) = 0;
err_out(1) = 0;
for n=1:10
ein=0;
				%training data
for i=1:train
	x=[data(i,1:iris_col-1)];
	h=sign(w*x');
	y=data(i,iris_col);
	if(y!=h)
		w_update = w_update + y*x;
		ein++;
	endif
endfor
err_in(n)=ein/train;
eout=0;
				%testing data
for i=1:test
	x=[data(i,1:iris_col-1)];
	h=sign(w*x');
	y=data(i,iris_col);
	if(y!=h)
		eout++;
	endif
endfor
err_out(n)=eout/test;
w=w_update;
endfor

weight_final=w;
save -append -text perceptron.txt weight_final
%dlmwrite("weight.txt", weight_final,"-append");

plot(err_in,"-;Ein;","color",'r');
hold on;
plot(err_in,"*","color",'r',err_out,":;Eout;","color",'k');
hold on;
plot(err_out,"d","color",'k');
xlabel("Epochs");
ylabel("Ein/Eout");
title("Evoluation of Ein and Eout");
print -dpng perceptron.png;
save -append -text perceptron.txt err_in
save -append -text perceptron.txt err_out
