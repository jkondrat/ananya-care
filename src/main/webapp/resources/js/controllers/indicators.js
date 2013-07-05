var care = angular.module('care');

Array.prototype.sortByName = function() {
    this.sort(function(a, b) {
        return (a.name > b.name);
    });
};

Array.prototype.sortById = function() {
    this.sort(function(a, b) {
        return (a.id < b.id);
    });
};

care.controller('createIndicatorController', function($scope, $http, $modal, $dialog, $filter, $location) {
    $scope.title = $scope.msg('indicators.title');

    $scope.addCategoryDisabled = true;
    $scope.addDimensionDisabled = true;

    $scope.indicator = {};
    $scope.indicator.complexConditions = [];
    $scope.indicator.values = [];
    $scope.complexConditions = [];
    $scope.categories = [];
    $scope.selectedOwners = {};
    $scope.condition = {};
    $scope.ownersValid = false;
    $scope.typeValid = false;
    $scope.categoriesValid = false;
    $scope.conditionsValid = false;
    $scope.newCondition = {};

    $scope.fetchUsers = function() {
        $http.get('api/users/')
            .success(function(users) {
                $scope.users = users;
            }).error(function() {
                $dialog.messageBox("Error", $scope.msg('indicators.form.error.cannotLoadUserList'), [{label: $scope.msg('ok'), cssClass: 'btn'}]).open();
            });
    };
    $scope.fetchUsers();

    $scope.fetchIndicatorTypes = function() {
        $http.get('api/indicator/type')
            .success(function(indicatorTypes) {
                indicatorTypes.sortByName();
                $scope.listIndicatorTypes = indicatorTypes;

                if (Object.keys($scope.listIndicatorTypes).length > 0) {
                    $scope.indicator.indicatorType = $scope.listIndicatorTypes[0].id;
                    $scope.typeValid = true;
                }
            }).error(function() {
                $dialog.messageBox("Error", $scope.msg('indicators.form.error.cannotLoadIndicatorTypeList'), [{label: $scope.msg('ok'), cssClass: 'btn'}]).open();
            });
    };
    $scope.fetchIndicatorTypes();

    $scope.fetchCategories = function() {
        $http.get('api/indicator/category')
            .success(function(categories) {
                categories.sortByName();
                $scope.listCategories = categories;

                if (Object.keys($scope.listCategories).length > 0) {
                    $scope.selectedCategory = "0";
                    $scope.addCategoryDisabled = false;
                }
            }).error(function() {
                $dialog.messageBox("Error", $scope.msg('indicators.form.error.cannotLoadCategoryList'), [{label: $scope.msg('ok'), cssClass: 'btn'}]).open();
            });
    };
    $scope.fetchCategories();

    $scope.removeUsedConditions = function() {
        var keyCount = Object.keys($scope.listComplexConditions).length;
        for (var i = keyCount - 1; i >= 0; i--) {
            var condition_value = $scope.listComplexConditions[i];
            if (!$scope.listComplexConditions.hasOwnProperty(i)) {
                continue;
            }

            for (var used_condition_key in $scope.complexConditions) {
                var used_condition_value = $scope.complexConditions[used_condition_key];
                if (!$scope.complexConditions.hasOwnProperty(used_condition_key)) {
                    continue;
                }

                if (condition_value.id == used_condition_value.id) {
                    $scope.listComplexConditions.splice(i, 1);
                }
            }
        }
    };

    $scope.fetchComplexConditions = function() {
        $http.get('api/complexcondition')
            .success(function(conditions) {
                conditions.sortById();
                $scope.listComplexConditions = conditions;

                if (Object.keys($scope.listComplexConditions).length > 0) {
                    $scope.removeUsedConditions();
                    $scope.selectedComplexCondition = "0";
                    $scope.addDimensionDisabled = false;
                }
            }).error(function() {
                $dialog.messageBox("Error", $scope.msg('indicators.form.error.cannotLoadComplexConditionList'), [{label: $scope.msg('ok'), cssClass: 'btn'}]).open();
            });
    }
    $scope.fetchComplexConditions();

    $scope.listFrequencies = [
        { name: "1 day", value: "1" },
        { name: "2 days", value: "2" },
        { name: "30 days", value: "30" },
        { name: "60 days", value: "60" },
        { name: "180 days", value: "180" }
    ];
    $scope.indicator.frequency = "30";
    $scope.listLevelDistrict = [
        { name: "District 9", value: "1" },
        { name: "District X", value: "2" },
        { name: "District Z", value: "3" }
    ];
    $scope.levelDistrict = "1";
    $scope.listLevelBlock = [
        { name: "Block 1", value: "1" },
        { name: "Block 2", value: "2" },
        { name: "Block 3", value: "3" }
    ];
    $scope.levelBlock = "1";
    $scope.listLevelSubcentre = [
        { name: "Subcentre A", value: "1" },
        { name: "Subcentre B", value: "2" },
        { name: "Subcentre C", value: "3" }
    ];
    $scope.levelSubcentre = "1";
    $scope.listLevelFlwType = [
        { name: "FLW 1", value: "1" },
        { name: "FLW 2", value: "2" },
        { name: "FLW 3", value: "3" }
    ];
    $scope.levelFlwType = "1";
    $scope.listOperators = [
        { name: "ADD", value: "1" },
        { name: "SUBTRACT", value: "2" },
        { name: "MULTIPLY", value: "3" },
        { name: "DIVIDE", value: "4" }
    ];
    $scope.listForms = [
        { name: "Form 1", value: "1" },
        { name: "Form 2", value: "2" }
    ];
    $scope.listCalculateBy = [
        { name: "Formula 1", value: "1" },
        { name: "Formula 2", value: "2" }
    ];
    $scope.listComparisonSymbols = [
        { name: "<=", value: "1" },
        { name: ">=", value: "2" }
    ];

    $scope.listComplexConditions = [
        { id: 0, operator: 1, form: 1, calculateBy: 1, comparisonSymbol: 1, comparisonValue: 10 },
        { id: 1, operator: 1, form: 1, calculateBy: 1, comparisonSymbol: 1, comparisonValue: 11 },
        { id: 2, operator: 1, form: 1, calculateBy: 1, comparisonSymbol: 1, comparisonValue: 12 }
    ];

    $scope.condition = {
        operator: "1",
        form: "1",
        calculateBy: "1",
        comparisonSymbol: "1",
        comparisonValue: 10
    };

    $scope.validateOwners = function() {
        $scope.ownersValid = false;

        for (var key in $scope.selectedOwners) {
            if ($scope.selectedOwners[key] === true) {
                $scope.ownersValid = true;
                return;
            }
        }
    }

    $scope.addCategory = function() {
        if ($scope.selectedCategory != null) {
            var category = $scope.listCategories[$scope.selectedCategory];

            if (category != null) {
                $scope.categories.push(category);
                $scope.categoriesValid = true;

                var index = $scope.listCategories.indexOf(category);
                if (index != -1) {
                    $scope.listCategories.splice(index, 1);

                    if (Object.keys($scope.listCategories).length <= 0) {
                        $scope.addCategoryDisabled = true;
                    }
                }
            }
        }
    };

    $scope.removeCategory = function(index) {
        $scope.listCategories.push($scope.categories[index]);
        $scope.categories.splice(index, 1);
        $scope.addCategoryDisabled = false;

        if (Object.keys($scope.categories).length <= 0) {
            $scope.categoriesValid = false;
        }
    }

    $scope.addComplexCondition = function() {
        if ($scope.selectedComplexCondition != null) {
            var complexCondition = $scope.listComplexConditions[$scope.selectedComplexCondition];

            if (complexCondition != null) {
                $scope.complexConditions.push(complexCondition);
                $scope.conditionsValid = true;

                var index = $scope.listComplexConditions.indexOf(complexCondition);
                if (index != -1) {
                    $scope.listComplexConditions.splice(index, 1);

                    if (Object.keys($scope.listComplexConditions).length <= 0) {
                        $scope.addDimensionDisabled = true;
                    }
                }
            }
        }
    };

    $scope.removeComplexCondition = function(index) {
        $scope.listComplexConditions.push($scope.complexConditions[index]);
        $scope.complexConditions.splice(index, 1);
        $scope.addDimensionDisabled = false;

        if (Object.keys($scope.complexConditions).length <= 0) {
            $scope.conditionsValid = false;
        }
    }

    $scope.getSelectedOwners = function() {
        var selectedOwners = [];

        for (var key in $scope.selectedOwners) {
            if ($scope.selectedOwners[key] === true) {
                selectedOwners.push(parseInt(key));
            }
        }

        return selectedOwners;
    };

    $scope.getSelectedCategories = function() {
        var selectedCategories = [];

        for (var key in $scope.categories) {
            if (!$scope.categories.hasOwnProperty(key)) {
                continue;
            }
            selectedCategories.push($scope.categories[key].id);
        }

        return selectedCategories;
    };

    $scope.submit = function() {
        $scope.indicator.owners = $scope.getSelectedOwners();
        $scope.indicator.categories = $scope.getSelectedCategories();
        $scope.indicator.level = 1;

        $http({
            url: "api/indicator",
            method: "POST",
            data: $scope.indicator,
            headers: { 'Content-Type': 'application/json' }
        }).success(function() {
                $location.path( "/" );
            }).error(function() {
                $dialog.messageBox("Error", $scope.msg('indicators.form.error.cannotCreateIndicator'), [{label: $scope.msg('ok'), cssClass: 'btn'}]).open();
        });
    };

    $scope.fetchOperatorTypes = function() {
        $http.get('api/complexcondition/operatortype')
            .success(function(operatorTypes) {
                operatorTypes.sortByName();
                $scope.listOperatorTypes = operatorTypes;

                if (Object.keys($scope.listOperatorTypes).length > 0) {
                    $scope.newCondition.operatorType = $scope.listOperatorTypes[0].id;
                }
            }).error(function() {
                $dialog.messageBox("Error", $scope.msg('indicators.form.error.cannotLoadOperatorTypeList'), [{label: $scope.msg('ok'), cssClass: 'btn'}]).open();
            });
    };

    $scope.fetchForms = function() {
        $http.get('api/forms')
            .success(function(forms) {
                forms.sort(function(a,b) { return a.displayName > b.displayName; });
                $scope.listForms = forms;

                if (Object.keys($scope.listForms).length > 0) {
                    $scope.newCondition.form = $scope.listForms[0].id;
                }
            }).error(function() {
                $dialog.messageBox("Error", $scope.msg('indicators.form.error.cannotLoadFormTypeList'), [{label: $scope.msg('ok'), cssClass: 'btn'}]).open();
            });
    };

    $scope.fetchFields = function() {
        var formId = $scope.newCondition.form;
        if (isNaN(formId) || !isFinite(formId)) {
            return;
        }

        console.log(formId);

        $http.get('api/forms/' + formId + "/fields")
            .success(function(fields) {
                fields.sort(function(a,b) { return a.displayName > b.displayName; });
                $scope.listFields = fields;

                if (Object.keys($scope.listFields).length > 0) {
                    $scope.newCondition.field = $scope.listFields[0];
                }
            }).error(function() {
                $dialog.messageBox("Error", $scope.msg('indicators.form.error.cannotLoadFieldList'), [{label: $scope.msg('ok'), cssClass: 'btn'}]).open();
            });
    };

    $scope.fetchComparisonSymbols = function() {
        $http.get('api/complexcondition/comparisonsymbol')
            .success(function(comparisonSymbols) {
                comparisonSymbols.sortByName();
                $scope.listComparisonSymbols = comparisonSymbols;

                if (Object.keys($scope.listComparisonSymbols).length > 0) {
                    $scope.newCondition.comparisonSymbol = $scope.listComparisonSymbols[0].id;
                }
            }).error(function() {
                $dialog.messageBox("Error", $scope.msg('indicators.form.error.cannotLoadComparisonSymbolList'), [{label: $scope.msg('ok'), cssClass: 'btn'}]).open();
            });
    };

    $scope.launchDialog = function() {
        $scope.fetchOperatorTypes();
        $scope.fetchForms();
        $scope.fetchComparisonSymbols();

        var dialog = $modal({
            template: "resources/partials/newComplexConditionDialog.html",
            persist: true,
            show: true,
            backdrop: "static",
            scope: $scope
        });
    };

    $scope.saveNewComplexCondition = function() {
        $scope.newCondition.indicators = [];

        $http({
            url: "api/complexcondition",
            method: "POST",
            data: $scope.newCondition,
            headers: { 'Content-Type': 'application/json' }
        }).success(function() {
                $scope.fetchComplexConditions();
            }).error(function() {
                $dialog.messageBox("Error", $scope.msg('indicators.form.error.cannotCreateNewComplexCondition'), [{label: $scope.msg('ok'), cssClass: 'btn'}]).open();
        });
    };

    $scope.$watch('newCondition.form', function() {
        $scope.fetchFields();
    });
});
