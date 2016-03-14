function plotData(X, y)
%PLOTDATA Plots the data points X and y into a new figure 
%   PLOTDATA(x,y) plots the data points with + for the positive examples
%   and o for the negative examples. X is assumed to be a Mx2 matrix.

% Create New Figure
figure; hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%
X_1=[];
X_0=[];
for i=1:size(X,1)

   if y(i)==1

   X_1=[X_1;X(i,:)];

   else

   X_0=[X_0;X(i,:)];

   end

end

plot(X_0(:,1),X_0(:,2),'ro',X_1(:,1),X_1(:,2),'g+');






% =========================================================================



hold off;

end
