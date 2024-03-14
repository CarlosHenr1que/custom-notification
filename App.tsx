import React from 'react';
import {Button, StyleSheet, View, NativeModules} from 'react-native';

function App(): React.JSX.Element {
  const createNotification = () => {
    NativeModules.CustomNotificationModule.createNotification(
      'My custom notification title',
      'My custom notfication description',
    );
  };

  return (
    <View style={styles.container}>
      <Button title="Start notification" onPress={createNotification} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#010101',
    alignItems: 'center',
    justifyContent: 'center',
  },
});

export default App;
