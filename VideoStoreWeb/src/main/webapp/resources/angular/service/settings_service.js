function SettingsService(module, serviceName) {
    module.factory(serviceName, ['$http', '$q', function ($http, $q) {
        
        var srvFactory = {
            fetchSettings: settingsLists,
            updateSettingData: updateSelectedSettingData,
			updateSettingDataList: updateSettingDataList,
            deleteSettingData: deleteSelectedSettingData
        };

        return srvFactory;

        function settingsLists() {
            var deferred = $q.defer();
            $http.get('/api/SettingsApi/LoadSettings')
                .then(
                function (response) {
                    deferred.resolve(response);
                },
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }

        function updateSettingDataList(models, UPDATE_SETTING_LIST_URL) {
            var deferred = $q.defer();
            $http({
                url: UPDATE_SETTING_LIST_URL,
                method: "PUT",
                data: JSON.stringify(models)
            }).then(
                function (response) {
                    deferred.resolve(response);
                },
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }

        function updateSelectedSettingData(model, UPDATE_SETTING_SELECTED_URL) {
            var deferred = $q.defer();
            $http({
                url: UPDATE_SETTING_SELECTED_URL,
                method: "POST",
                data: JSON.stringify(model)
            }).then(
                function (response) {
                    deferred.resolve(response);
                },
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }

        function deleteSelectedSettingData(settingID, DELETE_SELECTED_SETTING_URL) {
            var deferred = $q.defer();
            $http({
                url: DELETE_SELECTED_SETTING_URL,
                method: "DELETE",
                params: { SettingID: settingID}
            }).then(
                function (response) {
                    deferred.resolve(response);
                },
                function (errResponse) {
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
        }
    }]);
}