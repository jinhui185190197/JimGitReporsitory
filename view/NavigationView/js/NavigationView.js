/**
 * Created by waterchen on 2015/10/21.
 */
"use strict"
var React = require('react-native');

var {

    requireNativeComponent,
    PropTypes

    }=React;

class AndroidNavigationView extends React.Component {

    constructor(){
        super();
        this._onSelect = this._onSelect.bind(this);
    }

    _onSelect(event:Event) {
        if (!this.props.onMenuItemSelected) {
            return;
        }
        this.props.onMenuItemSelected(event.nativeEvent.menu_group, event.nativeEvent.menu_item);
    }

    render() {
        return <RCTAndroidNavigationView {...this.props} onSelect={this._onSelect}/>;
    }
}
;

AndroidNavigationView.propTypes = {
    items: PropTypes.array,
    items_group: PropTypes.array,
    item_icons: PropTypes.array,
    header_text1: PropTypes.string,
    header_text2: PropTypes.string,
    header_icon: PropTypes.string,
    onMenuItemSelected: PropTypes.func
};

var RCTAndroidNavigationView = requireNativeComponent('RCTNavigationView', AndroidNavigationView);
module.exports = AndroidNavigationView;