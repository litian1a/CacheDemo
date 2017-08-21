/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, {Component} from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    NativeModules,
    Image,
    View
} from 'react-native';


export default class CacheDemo extends Component {
    constructor(props){
        super(props);
        this.state={
            imgCache: '0'
        }

    }

    _clickShowImgCache(){
        NativeModules.ImgCacheModule.getImgCache((size)=>{this.setState({imgCache:size});alert('获取成功');},(error)=>{alert(error)});
    }
    _clickClearImgCache(){
        NativeModules.ImgCacheModule.clearImgCache((success)=>{ NativeModules.ImgCacheModule.getImgCache((size)=>{this.setState({imgCache:size})},(error)=>{});alert(success)},(error)=>{alert(error)});
    }
    render() {
        return (
            <View style={styles.container}>
            <Image style={{width:100,height:100}} source={{uri: 'https://facebook.github.io/react/img/logo_og.png'}}/>
            <Image style={{width:100,height:100}} source={{uri: 'http://ac-c6scxa78.clouddn.com/f6b64dc4bf7bee56.jpg'}}/>
                <Text onPress={this._clickShowImgCache.bind(this)} >获取缓存</Text>
                <Text >{this.state.imgCache+'b'}</Text>
                <Text onPress={this._clickClearImgCache.bind(this)} >清除缓存</Text>

            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
        flexDirection: 'column'
    }

});

AppRegistry.registerComponent('CacheDemo', () => CacheDemo);
