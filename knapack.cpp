#include <iostream>
#include <map>
#include <vector>
#include <algorithm>
using namespace std;

vector<double> knapsack(int p[], int w[], int capacity, int n) {
    vector<double> x(n, 0);
    map<double, vector<int>> mp;
    double pw[n];

    for (int i = 0; i < n; i++) {
        pw[i] = static_cast<double>(p[i]) / w[i];
        mp[pw[i]].push_back(i);
    }

    sort(pw, pw + n, greater<double>());

    int remainingCapacity = capacity;

    for (double ratio : pw) {
        if (mp.find(ratio) != mp.end()) {
            int j = mp[ratio][0];
            mp[ratio].erase(mp[ratio].begin());

            if (w[j] > remainingCapacity) {
                x[j] = static_cast<double>(remainingCapacity) / w[j];
                break;
            }
            x[j] = 1;
            remainingCapacity -= w[j];
        }
    }

    return x;
}

int main() {
    int p[] = {25, 24, 15};
    int w[] = {18, 15, 10};
    vector<double> result = knapsack(p, w, 20, 3);

    cout << "Fractions of items taken: ";
    for (double fraction : result) {
        cout << fraction << ",";
    }
    cout << endl;

    return 0;
}
