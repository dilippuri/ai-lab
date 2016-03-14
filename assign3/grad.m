function [w] = grad(name, iter, learnrate)
data = load(name); %load iris data into a matrix

[row col]=size(data); %size of input data

z = data(:,1:col-1);
y = data(:,col);
m = [ones(row,1) z];
weight_initial = zeros(1,col);

w = weight_initial;
learning_rate = learnrate;
iteration=iter;
save output1.txt name iteration learning_rate weight_initial

for i=1:iteration
	temp = m' * ( y - m*w' );
	w = w + 2*(learning_rate/row) * temp';
endfor			
plotDecisionBoundary(w,m,y);

endfunction
